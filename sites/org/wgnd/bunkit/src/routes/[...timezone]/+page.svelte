<script lang="ts">
  import { invalidate } from "$app/navigation";
  import { onMount } from "svelte";

  export let data;

  onMount(() => {
    const interval = setInterval(() => {
      invalidate("data:now");
    }, 1000);

    return () => {
      clearInterval(interval);
    };
  });
</script>

<h1>
  {new Intl.DateTimeFormat([], {
    timeStyle: "full",
    timeZone: data.timezone,
  }).format(new Date(data.now))}
</h1>
