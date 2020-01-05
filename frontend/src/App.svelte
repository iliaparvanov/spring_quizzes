<script>
	import Quiz from './Quiz.svelte';
	import ExpandedQuiz from './ExpandedQuiz.svelte'

	let displayQuizList = true;
	let current_quiz = null;
	let quizzes;
	
	let getQuizzes = async () => {
		let response = await fetch('http://localhost:8080/api/v1/quizzes');
		return await response.json();
	};

	quizzes = getQuizzes();

	const hideQuizList = (h) => {
		displayQuizList = !h;
		console.log(displayQuizList);
	}
	
	const takeQuiz = (quiz) => {
		hideQuizList(true);
		current_quiz = quiz;
	}
</script>

<button on:click={() => hideQuizList(false)}>
	Back to all quizzes
</button>

{#if displayQuizList}
	{#if quizzes === undefined}
		<p>Undefined</p>
	{:else}
		{#await quizzes}
			<p>Loading...</p>
		{:then data}
			{#each data as quiz}
				<Quiz quiz={quiz} on:takequiz={() => {takeQuiz(quiz)}}></Quiz>
			{/each}

		{:catch error}
			<p>{error.message}</p>

		{/await}
	{/if}
{:else}
	<ExpandedQuiz quiz={current_quiz}></ExpandedQuiz>
{/if}