import { createRouter, createWebHistory } from 'vue-router';
import UserForm from '@/user/UserForm.vue';
import UserList from '@/user/UserList.vue';

const routes = [
  {
    path: '/',
    name: 'Form',
    component: UserForm
  },
  {
    path: '/users',
    name: 'Users',
    component: UserList
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;