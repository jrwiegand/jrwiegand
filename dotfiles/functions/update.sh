update() {
	local all=false
	local mac=false
	local brew=false
	local rust=false

	while [ ! $# -eq 0 ]; do
		case "$1" in
		--all | -a)
			all=true
			;;
		--mac | -m)
			mac=true
			;;
		--brew | -b)
			brew=true
			;;
		--rust | -r)
			rust=true
			;;
		esac
		shift
	done

	if [ "$all" = true ] || [ "$mac" = true ]; then
		if ! type softwareupdate >/dev/null; then
			echo "\n\033[1mSkipping macOS update, 'softwareupdate' not available\033[0m\n"
		else
			echo "\n\033[1mUpdating macOS...\033[0m\n"
			softwareupdate -i -a
		fi
	fi

	if [ "$all" = true ] || [ "$brew" = true ]; then
		if ! type brew >/dev/null; then
			echo "\n\033[1mSkipping Homebrew update, 'brew' not available\033[0m\n"
		else
			echo "\n\033[1mUpdating Homebrew...\033[0m\n"
			brew update
			brew upgrade --greedy
			brew cleanup --prune=all
			brew doctor
		fi
	fi

	if [ "$all" = true ] || [ "$rust" = true ]; then
		if ! [ "$UPDATE_RUSTUP" = true ]; then
			echo "\n\033[1mSkipping rustup update, 'rustup' not available\033[0m\n"
		else
			echo "\n\033[1mUpdating rustup...\033[0m\n"
			rustup update
		fi
	fi
}
