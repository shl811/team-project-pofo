import { createApp } from 'vue'
import { createRouter, createWebHashHistory } from 'vue-router'

import App from './App.vue'
import Index from './components/Index.vue'
import Indexcopy from './components/Indexcopy.vue'

const routes = [
    { path: '/index', component: Index },
    { path: '/indexcopy', component: Indexcopy },
]

const router = createRouter({
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    history: createWebHashHistory(),
    routes, // short for `routes: routes`
})

createApp(App)
.use(router)
.mount('#app')
