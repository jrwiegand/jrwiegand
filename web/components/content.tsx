import { NAME } from "../lib/constants";

const Content = () => {
  return (
    <section className="py-4 text-gray-900 dark:text-gray-50">
      <h1 className="pb-4">{NAME}</h1>
      <h2 className="pb-4">Designer . Builder . Minimalist</h2>
      <h2 className="pb-4">
        @ <a href="https://stansberryresearch.com/" target="_blank" rel="noopener noreferrer" className="hover:underline">Stansberry Research</a>
      </h2>
      <ul>
        <li>
          <a href="mailto:hi@joshwiegand.com" className="hover:underline">
            Email
          </a>
        </li>
        <li>
          <a href="https://linkedin.com/in/jrwiegand" target="_blank" rel="noopener noreferrer" className="hover:underline">
            LinkedIn
          </a>
        </li>
        <li>
          <a href="https://github.com/jrwiegand" target="_blank" rel="noopener noreferrer" className="hover:underline">
            GitHub
          </a>
        </li>
        <li>
          <a href="https://twitter.com/joshrwiegand" target="_blank" rel="noopener noreferrer" className="hover:underline">
            Twitter
          </a>
        </li>
        <li>
          <a href="https://www.instagram.com/josh.wiegand" target="_blank" rel="noopener noreferrer" className="hover:underline">
            Instagram
          </a>
        </li>
      </ul>
    </section>
  );
};

export default Content;
