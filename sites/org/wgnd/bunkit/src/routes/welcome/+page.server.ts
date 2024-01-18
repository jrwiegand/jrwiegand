import { error, redirect } from "@sveltejs/kit";

export async function load({ cookies }) {
  if (!cookies.get("allowed")) {
    throw error(403, "Forbidden");
  }
}

export const actions = {
  default: ({ cookies }) => {
    cookies.delete("allowed", { path: "/" });
    throw redirect(303, "/");
  },
};
