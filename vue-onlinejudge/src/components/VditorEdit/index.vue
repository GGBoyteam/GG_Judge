<template>
  <div>
    <div :id="$props.name"></div>
  </div>
</template>
<script>
import {
  ref,
  watch,
  defineComponent,
  computed,
  onUpdated,
  onBeforeUpdate
} from "vue";
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
    onBeforeUpdate(() => {
      vditorEdit.value = new Vditor(props.name, {
        height: props.height,
        value: props.modelValue,
        mode: 'ir',
        cache:{
          enable:false
        },
        input(value) {
          context.emit('update:modelValue',value);
        }
      });
    });
    onUpdated(()=>{
      const valueId = computed({
        get: () => props.modelValue,
        set: (val) => {
          emit('update:modelValue',val)
        }
      });
      watch(valueId, (newValue) => {
        vditorEdit.value.setValue(newValue);
      });
    })
    return {
      vditorEdit
    };
  }
});
</script>