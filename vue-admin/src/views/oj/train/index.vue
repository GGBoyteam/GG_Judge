<template>
  <div id="vditor"></div>
</template>
<script setup>
import { defineProps, ref, onMounted } from "vue";
import Vditor from "vditor";
import "vditor/dist/index.css";
const contentEditor = ref("");
onMounted(() => {
  contentEditor.value = new Vditor("vditor", {
    height: 360,
    toolbarConfig: {
      pin: true,
    },
    cache: {
      enable: false,
    },
    after: () => {
      contentEditor.value.setValue("hello,Vditor+Vue!");
    },
  });
});


</script>