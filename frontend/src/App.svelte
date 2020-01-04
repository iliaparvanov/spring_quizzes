<script>
	import Quiz from './Quiz.svelte';
	import ExpandedQuiz from './ExpandedQuiz.svelte'

	let displayQuizList = true;
	let current_quiz = null;
	let quizzes = [
		{
			id: 1,
			name: "Biology1",
			content: [
				{
					question: "Which famous scientist introduced the idea of natural selection?",
					answers: [
						{
							answer: "Charles Darwin",
							is_correct: 1
						},
						{
							answer: "Pesho Peshev",
							is_correct: 0
						},
						{
							answer: "Isaac Newton",
							is_correct: 0
						},
						{
							answer: "Louis Pasteur",
							is_correct: 0
						}
					] 
				},
				{
					question: "What are biologists interested in?",
					answers: [
						{
							answer: "flora",
							is_correct: 1
						},
						{
							answer: "music",
							is_correct: 0
						},
						{
							answer: "fauna",
							is_correct: 1
						},
						{
							answer: "literature",
							is_correct: 0
						}
					] 
				},
				{
					question: "A person who studies biology is known as a",
					answers: [
						{
							answer: "radiologist",
							is_correct: 0
						},
						{
							answer: "biologist",
							is_correct: 1
						},
						{
							answer: "scientologist",
							is_correct: 0
						},
						{
							answer: "oncologist",
							is_correct: 0
						}
					] 
				},
				
			]
		},
		{
			id: 2,
			name: "Math1",
			content: [
				{
					question: "1+1=",
					answers: [
						{
							answer: "-15",
							is_correct: 0
						},
						{
							answer: "3",
							is_correct: 0
						},
						{
							answer: "1.5",
							is_correct: 0
						},
						{
							answer: "2",
							is_correct: 1
						}
					] 
				}
			]
		}
	];
	
	
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
	{#each quizzes as quiz}
		<Quiz quiz={quiz} on:takequiz={takeQuiz}></Quiz>
	{/each}
{:else}
	<ExpandedQuiz quiz={current_quiz}></ExpandedQuiz>
{/if}