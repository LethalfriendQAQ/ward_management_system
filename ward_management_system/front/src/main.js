import { createApp } from 'vue'
import { createPinia } from 'pinia'
//持久化插件
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from './router'
import './assets/main.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }

const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);
app.use(pinia)
app.use(router)

app.use(ElementPlus, {
  locale: zhCn,
})
app.use(ElementPlus)
app.mount('#app')
