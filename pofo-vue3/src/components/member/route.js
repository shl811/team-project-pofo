import CommunityRegister from './CommunityRegister.vue'

const member = {
    path: '/member',
    children: [
        {
            path: 'communityRegister',
            component: CommunityRegister
        }
    ] 
}

export default member;