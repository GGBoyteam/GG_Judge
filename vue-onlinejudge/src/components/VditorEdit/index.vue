<template>
  <div>
    <div :id="$props.name"></div>
  </div>
</template>
<script>
import {ref, onMounted, watch, defineComponent, defineEmits, computed} from "vue";
import Vditor from "vditor";
import "vditor/dist/index.css";
const vditorEdit = ref("");
export default defineComponent({
  name: 'vditorEdit',
  props: {
    modelValue:String,
    height: {
      default: 600
    },
    name:{
      type: String
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
      vditorEdit.value = new Vditor(props.name, {
        height: props.height,
        value: props.modelValue,
        mode: 'ir',
        cache:{
          enable: false
        },
        input(value) {
          context.emit('update:modelValue',value);
        }
      });
    });

    // 监听props.modelValue的变化
    watch(valueId, (newValue) => {
      // do something when modelValue changes
      console.log('modelValue changed to:', newValue);
    });
    return {
      vditorEdit
    };
  },
});
</script>