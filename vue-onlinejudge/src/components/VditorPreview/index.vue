<template>
  <div>
    <div :id="$props.name"></div>
  </div>
</template>
<script>
import {ref, onMounted, defineComponent, watch, computed} from "vue";
import Vditor from "vditor";
import "vditor/dist/index.css";
import VditorSetting from 'vditor/dist/method.min'
const vditorPreview = ref("");
export default defineComponent({
  name: 'vditorPreview',
  props: {
    name: {
      type: String
    },
    modelValue:{
      type:String
    },
    height: {
      type: String,
      default: '360'
    }
  },
  setup(props,context) {
    const valueId = computed({
      get: () => props.modelValue,
      set: (val) => {
        emit('update:modelValue',val)
      }
    });
    onMounted(() => {
      vditorPreview.value = new Vditor(props.name, {
        height: '360',
        mode: 'ir',
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

    // 监听props.modelValue的变化
    watch(valueId, (newValue) => {
      // do something when modelValue changes
      console.log('dawdawdmodelValue changed to:', newValue);
      VditorSetting.preview(document.getElementById(props.name),props.modelValue, {
        hljs: { style: "github" },
      });
    });
    return {
      vditorPreview
    };
  },
});
</script>