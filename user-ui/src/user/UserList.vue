<template>
  <div class="min-vh-100 d-flex flex-column bg-light">
    <AppHeader class="fixed-top shadow-sm" />

    <div class="container" style="padding-top: 120px; padding-bottom: 40px;">
      <h2 class="mb-4 text-center table-title">
        Lista de Usuários
      </h2>

      <div v-if="loading" class="text-center my-4">
        <div class="spinner-border text-primary" role="status">
          <span class="visually-hidden">Carregando...</span>
        </div>
      </div>

      <div v-if="errorMessage" class="alert alert-danger text-center">
        {{ errorMessage }}
      </div>

      <div v-if="!loading && users.length === 0 && !errorMessage" class="alert alert-info text-center">
        Nenhum usuário encontrado.
      </div>

      <div v-if="users.length > 0" class="table-responsive d-none d-md-block">
        <table class="table table-hover align-middle rounded shadow-sm">
          <thead class="bg-primary text-white">
            <tr>
              <th>ID</th>
              <th>Nome</th>
              <th>Documento</th>
              <th>Email</th>
              <th>Telefone</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(user, index) in users" :key="user.id" class="table-row-hover">
              <td>{{ user.id }}</td>

              <td>
                <input v-if="editingIndex === index" v-model="editableUser.name" class="form-control form-control-sm" />
                <span v-else>{{ user.name }}</span>
              </td>

              <td>
                <input v-if="editingIndex === index" v-model="editableUser.doc" class="form-control form-control-sm" />
                <span v-else>{{ user.doc }}</span>
              </td>

              <td>
                <input v-if="editingIndex === index" v-model="editableUser.email" class="form-control form-control-sm" />
                <span v-else>{{ user.email }}</span>
              </td>

              <td>
                <input v-if="editingIndex === index" v-model="editableUser.phone" class="form-control form-control-sm" />
                <span v-else>{{ user.phone }}</span>
              </td>

              <td class="d-flex gap-1 flex-wrap">
                <button v-if="editingIndex !== index" @click="startEditing(index)" class="btn btn-sm btn-info text-white btn-hover-zoom">
                  Editar
                </button>
                <button v-if="editingIndex !== index" @click="deleteUser(index)" class="btn btn-sm btn-danger btn-hover-zoom">
                  Deletar
                </button>
                <button v-else @click="saveUser(index)" class="btn btn-sm btn-success btn-hover-zoom">
                  Salvar
                </button>
                <button v-if="editingIndex === index" @click="cancelEditing()" class="btn btn-sm btn-secondary btn-hover-zoom">
                  Cancelar
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <div v-if="users.length > 0" class="d-block d-md-none">
        <div v-for="(user, index) in users" :key="user.id" class="card mb-3 shadow-sm card-hover">
          <div class="card-body">
            <!-- Nome -->
            <h5 class="card-title" style="color: #14AFA0;">
              <input v-if="editingIndex === index" v-model="editableUser.name" class="form-control form-control-sm mb-2" />
              <span v-else>{{ user.name }}</span>
            </h5>

            <p class="card-text"><strong>ID:</strong> {{ user.id }}</p>
            <p class="card-text">
              <strong>Documento:</strong>
              <input v-if="editingIndex === index" v-model="editableUser.doc" class="form-control form-control-sm" />
              <span v-else>{{ user.doc }}</span>
            </p>
            <p class="card-text">
              <strong>Email:</strong>
              <input v-if="editingIndex === index" v-model="editableUser.email" class="form-control form-control-sm" />
              <span v-else>{{ user.email }}</span>
            </p>
            <p class="card-text">
              <strong>Telefone:</strong>
              <input v-if="editingIndex === index" v-model="editableUser.phone" class="form-control form-control-sm" />
              <span v-else>{{ user.phone }}</span>
            </p>

            <div class="d-flex gap-2 flex-wrap mt-2">
              <button v-if="editingIndex !== index" @click="startEditing(index)" class="btn btn-sm btn-info text-white btn-hover-zoom">
                Editar
              </button>
              <button v-if="editingIndex !== index" @click="deleteUser(index)" class="btn btn-sm btn-danger btn-hover-zoom">
                Deletar
              </button>
              <button v-else @click="saveUser(index)" class="btn btn-sm btn-success btn-hover-zoom">
                Salvar
              </button>
              <button v-if="editingIndex === index" @click="cancelEditing()" class="btn btn-sm btn-secondary btn-hover-zoom">
                Cancelar
              </button>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script setup lang="ts">
import AppHeader from '@/shared/AppHeader.vue';
import { onMounted, ref } from 'vue';
import axios from 'axios';

const users = ref<any[]>([]);
const loading = ref(false);
const errorMessage = ref('');

const editingIndex = ref<number | null>(null);
const editableUser = ref<any>({});

onMounted(async () => {
  await fetchUsers();
});

const fetchUsers = async () => {
  loading.value = true;
  try {
    const response = await axios.get('http://localhost:8080/users');
    users.value = response.data;
  } catch (error: any) {
    errorMessage.value = 'Erro ao carregar usuários.';
    console.error(error);
  } finally {
    loading.value = false;
  }
};

const deleteUser = async (index: number) => {
  if (!confirm('Tem certeza que deseja deletar este usuário?')) return;
  loading.value = true;
  try {
    const userId = users.value[index].id;
    await axios.delete(`http://localhost:8080/users/${userId}`);
    users.value.splice(index, 1);
  } catch (error: any) {
    errorMessage.value = 'Erro ao deletar usuário.';
    console.error(error);
  } finally {
    loading.value = false;
  }
};

const startEditing = (index: number) => {
  editingIndex.value = index;
  editableUser.value = { ...users.value[index] };
};

const cancelEditing = () => {
  editingIndex.value = null;
  editableUser.value = {};
};

const saveUser = async (index: number) => {
  try {
    const userId = users.value[index].id;
    await axios.put(`http://localhost:8080/users/${userId}`, editableUser.value, {
      headers: { 'Content-Type': 'application/json' }
    });
    users.value[index] = { ...editableUser.value };
    cancelEditing();
  } catch (error: any) {
    console.error('Erro ao salvar usuário:', error);
    alert('Erro ao salvar usuário.'); 
  }
};
</script>

<style scoped>
.btn-hover-zoom {
  transition: transform 0.2s ease-in-out;
}
.btn-hover-zoom:hover {
  transform: scale(1.05);
}

.table-row-hover {
  transition: background-color 0.3s, color 0.3s;
}
.table-row-hover:hover {
  background-color: #1DEFE0 !important;
  color: #145C5E;
}

.table-title {
  color: #14AFA0;
  font-weight: 700;
  text-shadow: 1px 1px 2px rgba(0,0,0,0.1);
}

table thead th {
  font-weight: 600;
  letter-spacing: 0.5px;
}

table tbody tr:nth-child(odd) {
  background-color: #F8F9FA;
}
table tbody tr:nth-child(even) {
  background-color: #e0f7f7;
}

.card-hover {
  transition: transform 0.2s, box-shadow 0.2s;
}
.card-hover:hover {
  transform: scale(1.02);
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.15);
}
</style>
