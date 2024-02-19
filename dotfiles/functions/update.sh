update() {
	local all=false
	local mac=false
	local brew=false

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
		esac
		shift
	done

	if [ "$all" = true ] || [ "$mac" = true ]; then
		if ! type softwareupdate >/dev/null; then
			echo "Skipping macOS update, 'softwareupdate' not available\n"
		else
			echo "Updating macOS...\n"
			softwareupdate -i -a
		fi
	fi

	if [ "$all" = true ] || [ "$brew" = true ]; then
		if ! type brew >/dev/null; then
			echo "Skipping Homebrew update, 'brew' not available\n"
		else
			echo "Updating Homebrew..."
			brew update
			brew upgrade --greedy
			brew cleanup --prune=all
			brew doctor
		fi
	fi
}
