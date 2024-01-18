<script lang="ts">
  import { invalidateAll } from "$app/navigation";
  import { onMount } from "svelte";

  export let data;

  onMount(() => {
    const interval = setInterval(() => {
      invalidateAll();
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
