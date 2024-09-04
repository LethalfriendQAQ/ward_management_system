import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import PatientView from '@/views/PatientView.vue'
import DepartmentView from '@/views/DepartmentView.vue'
import IndexView from '@/views/IndexView.vue'
import NurseView from '@/views/NurseView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      redirect: '/index',
      children: [
        {
          path: '/patient',
          component: PatientView
        }, {
          path: '/dept',
          component: DepartmentView
        }, {
          path: '/index',
          component: IndexView
        }, {
          path: '/nurse',
          component: NurseView
        }
      ]
    }, {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    }
  ]
})

export default router
