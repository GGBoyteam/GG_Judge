<template>
  <div>
    <div :id="$props.name"></div>
  </div>
</template>
<script>
import {ref, onMounted, defineComponent} from "vue";
import Vditor from "vditor";
import "vditor/dist/index.css";
import VditorSetting from 'vditor/dist/method.min'
const vditorPreview = ref("");
export default defineComponent({
  name: 'vditorPreview',
  props: {
    name: String,
    modelValue:String,
    height: {
      type: String,
      default: '360'
    }
  },
  setup(props,context) {
    const text=ref('');
    onMounted(() => {
      console.log("初始化")
      vditorPreview.value = new Vditor(props.name, {
        height: props.height,
        mode: 'ir',
        toolbarConfig:{
          hide: true,
          pin: true
        },
        cache:{
          enable: false
        },
        after() {
          VditorSetting.preview(document.getElementById(props.name),props.modelValue, {
            hljs: { style: "github" },
          });
        }
      });
    });

    return {
      vditorPreview
    };
  },
});
</script>