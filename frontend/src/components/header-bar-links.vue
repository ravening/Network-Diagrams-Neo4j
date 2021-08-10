<template>
  <div class="navbar-menu">
    <div class="navbar-end">
      <div class="navbar-item">
        <div class="buttons">
          <a :href="github" target="_blank" rel="noopener noreferrer">
            <i class="fab fa-github fa-2x" aria-hidden="true"></i>
          </a>
          <a :href="neo4j" target="_blank" rel="noopener noreferrer">
            <i class="fa fa-database fa-2x" aria-hidden="true"></i>
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      github: 'https://github.com/ravening/Network-Diagrams-Neo4j',
      neo4j: 'http://10.135.100.5:7474/browser/',
    };
  },
};
</script>
