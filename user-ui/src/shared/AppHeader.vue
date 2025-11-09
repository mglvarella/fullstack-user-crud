<template>
  <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #18CBBF">
    <div class="container">
      <a class="navbar-brand" href="#">Meu App</a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <router-link class="nav-link" to="/">Cadastro</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/users">Usuários</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>


<script setup></script>

<style scoped>
.nav-link {
  transition: color 0.2s;
  color: white;
  font-weight: 500;
}

.nav-link:hover {
  color: #e0f7f5;
  transform: scale(1.05);
}

header, .navbar {
  color: white;
  font-weight: 600;
  text-shadow: 0 1px 2px rgba(0,0,0,0.3);
}
</style>
