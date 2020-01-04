<script>
	import {onMount, afterUpdate, onDestroy} from "svelte";

	export let quiz;
	
	onMount(() => {
			console.log("Mounted");
	});

	afterUpdate(() => {
			console.log("Updated");
	});

	onDestroy(() => {
			console.log("Destroyed");
	});
</script>

{#if quiz === null}
<p>
	No quiz selected!
</p>
{:else}
<div id={`quiz-${quiz.id}`}>
	<p>
		Taking {quiz.name}
	</p>
</div>
{/if}
