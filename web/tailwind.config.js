module.exports = {
  darkMode: "media",
  purge: {
    enabled: true,
    content: ["./templates/base.html", "./templates/index.html"],
  },
  theme: {
    container: {
      center: true,
      padding: "2rem",
    },
  },
};
