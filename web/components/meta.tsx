import Head from "next/head";
import { NAME } from "../lib/constants";

const Meta = () => {
  return (
    <Head>
      <title>{NAME}</title>

      <meta name="description" content="Josh Wiegand's Homepage" />
      <meta property="og:title" content="Homepage - Josh Wiegand" />
      <meta property="og:description" content="Josh Wiegand's Homepage" />
      <meta property="og:image" content={`${URL}/profile.jpg`} />
      <meta property="og:image:alt" content="Josh Wiegand" />
      <meta property="og:locale" content="en_US" />
      <meta property="og:type" content="website" />
      <meta name="twitter:card" content="summary_large_image" />
      <meta property="og:url" content={`${URL}`} />
      <link rel="canonical" href={`${URL}`} />

      <link rel="icon" href="/favicon.ico" />
      <link rel="icon" href="/favicon.svg" type="image/svg+xml" />
      <meta name="theme-color" content="#111827" />
    </Head>
  );
};

export default Meta;
