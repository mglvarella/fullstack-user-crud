<script setup lang="ts">
import AppHeader from '@/shared/AppHeader.vue';

</script>

<template >
  <div class="container-fluid bg-body-tertiary min-vh-100 d-flex justify-content-center align-items-center flex-column">
    <AppHeader></AppHeader>
    <form class="p-4 bg-light rounded shadow-sm w-100" style="max-width: 600px;">
      <div class="mb-3">
        <label for="name" class="form-label">Nome do usuário</label>
        <input type="text" class="form-control" id="name" placeholder="Seu nome completo" />
      </div>

      <div class="mb-3">
        <label for="doc" class="form-label">CPF/CNPJ</label>
        <input type="text" class="form-control" id="doc" placeholder="Digite seu CPF ou CNPJ" />
      </div>

      <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" id="email" placeholder="seu@email.com" />
      </div>

      <div class="mb-3">
        <label for="phone" class="form-label">Telefone</label>
        <input type="email" class="form-control" id="phone" placeholder="(xx) xxxxx-xxxx" />
      </div>

      <div class="text-end">
        <button type="button" class="btn btn-outline-secondary me-2">Cancelar</button>
        <button type="submit" class="btn btn-primary">Enviar</button>
      </div>
    </form>
  </div>
</template>
