import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import PatientView from '@/views/PatientView.vue'
import DepartmentView from '@/views/DepartmentView.vue'
import IndexView from '@/views/IndexView.vue'
import NurseView from '@/views/NurseView.vue'
import WardView from '@/views/WardView.vue'
import BedView from '@/views/BedView.vue'
import DutyView from '@/views/DutyView.vue'
import LoginView from '@/views/LoginView.vue'
import {useTokenStore} from "@/stores/token.js";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: LoginView
    }, {
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
        }, {
          path: '/ward',
          component: WardView
        }, {
          path: '/bed',
          component: BedView
        }, {
          path: '/duty',
          component: DutyView
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
});

//导航守卫
router.beforeEach((to, from) => {
  //to - 要访问的位置
  //from - 其实位置
  if(to.path == '/login') {
    return true;
  } else {
    const tokenStore = useTokenStore();

    //判断store中是否有token
    if(!tokenStore.token) {
      return "/login";
    } else {
      return true;
    }
  }
});


export default router
