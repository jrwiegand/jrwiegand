#!/bin/sh

export REPO_DIR="$(dirname $(readlink "$HOME"/.zshrc))"

if [ ! -d "$HOME"/.nvm ]; then
    mkdir "$HOME"/.nvm
fi

# link directories
ln -sfv "$REPO_DIR"/.vim                     "$HOME"/

# link files
ln -sfv "$REPO_DIR"/.zshrc                   "$HOME"/.zshrc
ln -sfv "$REPO_DIR"/.vimrc                   "$HOME"/.vimrc
ln -sfv "$REPO_DIR"/config.toml              "$HOME"/.config/helix/config.toml
ln -sfv "$REPO_DIR"/zed/debug.json           "$HOME"/.config/zed/debug.json
ln -sfv "$REPO_DIR"/zed/keymap.json          "$HOME"/.config/zed/keymap.json
ln -sfv "$REPO_DIR"/zed/settings.json        "$HOME"/.config/zed/settings.json
ln -sfv "$REPO_DIR"/zed/settings_backup.json "$HOME"/.config/zed/settings_backup.json
