import Register from './community/Register.vue'

const member = {
    path: '/member',
    children: [
        {
            path: 'community',
            children: [
                { 
                    path: 'register', 
                    component: Register 
                }
            ]
        }
    ] 
}

export default member;