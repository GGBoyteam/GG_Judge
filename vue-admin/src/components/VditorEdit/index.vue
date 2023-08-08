<template>
  <div>
    <div id="vditorEdit"></div>
  </div>
</template>
<script>
import {ref, onMounted, watch, defineComponent, defineEmits} from "vue";
import Vditor from "vditor";
import "vditor/dist/index.css";
const vditorEdit = ref("");
export default defineComponent({
  name: 'vditorEdit',
  props: {
    modelValue:String,
    height: {
      type: String,
      default: 'auto'
    }
  },
  setup(props,context) {
    const text=ref('');
    onMounted(() => {
      vditorEdit.value = new Vditor('vditorEdit', {
        height: props.height,
        input(value) {
          text.value=value;
        }
      });
    });
    watch(() => text.value,(newVal,oldVal)=>{
      context.emit('update:modelValue',newVal);
    })
    return {
      vditorEdit
    };
  },
});
</script>