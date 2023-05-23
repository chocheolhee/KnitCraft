<script setup lang="ts">
import {ref} from "vue";

const count = ref(0)
</script>

<template>
  <p>hello</p>
  <button @click="count += 1">push</button>
  <p>{{ count }}</p>
</template>

<style>

</style>