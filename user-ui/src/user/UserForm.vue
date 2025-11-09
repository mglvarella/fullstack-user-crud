<template>
  <div>
    <AppHeader class="fixed-top shadow-sm" />

     <div class="container-fluid min-vh-100 d-flex justify-content-center align-items-center flex-column" style="padding-top: 70px;">
      
      <form class="p-4 bg-light rounded shadow-sm w-100" style="max-width: 600px;" @submit="handleSubmit">
        <h2 class="mb-4 text-center" style="color: #14AFA0; font-weight: 700; text-shadow: 1px 1px 2px rgba(0,0,0,0.1);">
            Cadastro
        </h2>

        <div class="mb-3">
          <label for="name" class="form-label">Nome do usuário</label>
          <input type="text" class="form-control" id="name" placeholder="Seu nome completo" v-model="formData.name" />
          <div v-if="errors.name" class="text-danger mt-1">{{ errors.name }}</div>
        </div>

        <div class="mb-3">
          <label for="doc" class="form-label">CPF/CNPJ</label>
          <input type="text" class="form-control" id="doc" placeholder="Digite seu CPF ou CNPJ" v-model="formData.doc" />
          <div v-if="errors.doc" class="text-danger mt-1">{{ errors.doc }}</div>
        </div>

        <div class="mb-3">
          <label for="email" class="form-label">Email</label>
          <input type="email" class="form-control" id="email" placeholder="seu@email.com" v-model="formData.email" />
          <div v-if="errors.email" class="text-danger mt-1">{{ errors.email }}</div>
        </div>

        <div class="mb-3">
          <label for="phone" class="form-label">Telefone</label>
          <input type="tel" class="form-control" id="phone" placeholder="(xx) xxxxx-xxxx" v-model="formData.phone" />
          <div v-if="errors.phone" class="text-danger mt-1">{{ errors.phone }}</div>
        </div>

        <div class="mb-3 text-center">
          <div v-if="successMessage" class="text-success mb-2">{{ successMessage }}</div>
          <div v-if="errorMessage" class="text-danger mb-2">{{ errorMessage }}</div>
        </div>

        <div class="text-end">
          <button type="button" class="btn btn-outline-secondary me-2" :disabled="loading">Cancelar</button>
          <button type="submit" class="btn btn-primary" :disabled="loading">
            <span v-if="loading" class="spinner-border spinner-border-sm me-1"></span>
            Enviar
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import AppHeader from '@/shared/AppHeader.vue';
import { onMounted, reactive, ref } from 'vue';
import axios from 'axios';
import IMask from 'imask';

const api = axios.create({
  baseURL: import.meta.env.VITE_APP_API_URL
});

const phoneInput = ref<HTMLInputElement | null>(null);
const docInput = ref<HTMLInputElement | null>(null);

onMounted(() => {
  if (phoneInput.value) {
    IMask(phoneInput.value, {
      mask: '(00) 00000-0000'
    });
  }

  if (docInput.value) {
    IMask(docInput.value, [
      { mask: '000.000.000-00' },
      { mask: '00.000.000/0000-00' }
    ]);
  }
});

const formData = reactive({
  name: '',
  doc: '',
  email: '',
  phone: ''
});

const errors = reactive({
  name: '',
  doc: '',
  email: '',
  phone: ''
});

const loading = ref(false);
const successMessage = ref('');
const errorMessage = ref('');

const validateForm = () => {
  let valid = true;
  errors.name = '';
  errors.doc = '';
  errors.email = '';
  errors.phone = '';

  if (!formData.name.trim()) {
    errors.name = 'O nome é obrigatório';
    valid = false;
  }

  if (!formData.doc.trim()) {
    errors.doc = 'O documento é obrigatório';
    valid = false;
  }

  if (!formData.email.trim()) {
    errors.email = 'O email é obrigatório';
    valid = false;
  } else if (!/^\S+@\S+\.\S+$/.test(formData.email)) {
    errors.email = 'Formato de email inválido';
    valid = false;
  }

  if (!formData.phone.trim()) {
    errors.phone = 'O telefone é obrigatório';
    valid = false;
  }

  return valid;
};

const handleSubmit = async (e: Event) => {
  e.preventDefault();

  if (!validateForm()) return;

  loading.value = true;
  successMessage.value = '';
  errorMessage.value = '';

  try {
    const response = await api.post('/users', formData, {
      headers: { 'Content-Type': 'application/json' }
    });

    successMessage.value = 'Usuário cadastrado com sucesso!';
    console.log('Resposta do backend:', response.data);

    formData.name = '';
    formData.doc = '';
    formData.email = '';
    formData.phone = '';

  } catch (error: any) {
    console.error('Erro ao enviar dados:', error);
    errorMessage.value = error.response?.data?.message || 'Erro ao enviar formulário.';
  } finally {
    loading.value = false;
  }
};
</script>
