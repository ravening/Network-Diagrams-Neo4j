import Vue from 'vue';
import Router from 'vue-router';
import PageNotFound from '@/views/page-not-found';

Vue.use(Router);

const parseProps = r => ({ id: parseInt(r.params.id) });

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      redirect: '/customers',
    },
    {
      path: '/customers',
      name: 'customers',
      component: () =>
        import(/* webpackChunkName: "bundle.heroes" */ './views/heroes.vue'),
    },
    {
      path: '/customers/:id',
      name: 'customer-details',
      props: true,
      component: () =>
        import(/* webpackChunkName: "bundle.heroes" */ './views/hero-detail.vue'),
    },
    {
      path: '/apis',
      name: 'apis',
      component: () =>
        import(/* webpackChunkName: "bundle.villains" */ './views/villains.vue'),
    },
    {
      path: '/apis/:id',
      name: 'api-details',
      props: true,
      // props: parseProps,
      component: () =>
        import(/* webpackChunkName: "bundle.villains" */ './views/villain-detail.vue'),
    },
    {
      path: '/about',
      name: 'about',
      component: () =>
        import(/* webpackChunkName: "bundle.about" */ './views/about.vue'),
    },
    {
      path: '*',
      component: PageNotFound,
    },
  ],
});
