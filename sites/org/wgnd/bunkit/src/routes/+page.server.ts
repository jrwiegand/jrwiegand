import { env } from "$env/dynamic/private";
import { fail, redirect } from "@sveltejs/kit";

export async function load({ cookies }) {
  if (cookies.get("allowed")) {
    throw redirect(307, "/welcome");
  }
}

export const actions = {
  default: async ({ request, cookies }) => {
    const data = await request.formData();

    if (data.get("passphrase") === env.PASSPHRASE) {
      cookies.set("allowed", "true", {
        path: "/",
      });

      throw redirect(303, "/welcome");
    }

    return fail(403, {
      incorrect: true,
    });
  },
};
