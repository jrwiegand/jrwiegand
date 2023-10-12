// place files you want to import through the `$lib` alias in this folder.
export async function getRandomNumber() {
    // Fetch a random number between 0 and 100
    // (with a delay, so that we can see it)
    const res = await fetch('https://www.random.org/integers/?num=1&min=0&max=1000000&col=1&base=10&format=plain&rnd=new');

    if (res.ok) {
        return await res.text();
    } else {
        // Sometimes the API will fail!
        throw new Error('Request failed');
    }
}