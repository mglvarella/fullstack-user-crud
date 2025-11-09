<template>
  <header class="bg-primary text-white py-3 fixed-top">
    <div class="container d-flex justify-content-between align-items-center">
      <h1 class="h5 m-0">Tech test</h1>
      <nav>
        <ul class="nav">
          <li class="nav-item"><a class="nav-link text-white" href="#">Home</a></li>
          <li class="nav-item"><a class="nav-link text-white" href="#">Sobre</a></li>
          <li class="nav-item"><a class="nav-link text-white" href="#">Contato</a></li>
        </ul>
      </nav>
    </div>
  </header>
</template>

<script setup></script>

<style scoped>
.nav-link {
  transition: color 0.2s;
}
.nav-link:hover {
  color: #ffc107;
}
</style>
