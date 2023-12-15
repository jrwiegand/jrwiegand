export function load({ cookies }) {
    const visited = cookies.get('visited');

    cookies.set('visited', 'true', { path: '/', maxAge: 86400 });

    return {
        visited
    };
}
