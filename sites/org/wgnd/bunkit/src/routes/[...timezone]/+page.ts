export async function load({ params }) {
  return {
    timezone: params.timezone,
  };
}
