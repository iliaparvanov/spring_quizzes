<script>
	import { createEventDispatcher } from "svelte";
  const dispatch = createEventDispatcher();
	
	export let quiz;
	
	const takeQuiz = () => dispatch("takequiz", quiz)
</script>


	<div>
		<h3>
			{quiz.name}
		</h3>
		<button on:click={takeQuiz}>
			Take {quiz.name}
		</button>
	</div>
