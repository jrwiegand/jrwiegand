## get ip information
ip() { curl https://ipinfo.io/"$1"; }

## get crypto rates
cr() { (IFS=+; curl https://rate.sx/"$*"; ); }

## kill a process based on the port number
kp() { kill -9 $(lsof -i :"$1" -t); }

## hash content of the file line-by-line
hash_md5() {
	filename=$(basename -- "$1")
	filename="${filename%.*}"
	cat "$1" | while read line; do
		echo -n "$line" | md5 >>"$filename"_hashed_md5.csv;
	done
}

hash_sha_256() {
	filename=$(basename -- "$1")
	filename="${filename%.*}"
	cat "$1" | while read line; do
		echo -n "$line" | shasum -a 256 >>"$filename"_hashed_256.csv;
	done
}

## check status on a given url and notify
cs() {
	local status_result=$(curl -I -L -s "$1" | rg 200)
	if [ -z "$status_result" ]; then

	else
		terminal-notifier -title "Success!" -message "Click to open URL" -open "$1"
	fi
}

## generate lorem text
lorem() {
	local paragraphs="${1}"
	local sentences="${2}"

	if [ "$paragraphs" = "" ]; then
		local paragraphs=1
	fi
	if [ "$sentences" = "" ]; then
		local sentences=1
	fi

	curl -s http://metaphorpsum.com/paragraphs/"${paragraphs}"/"${sentences}" | pbcopy

	echo "Saved to clipboard..."
}

# Make PDF file with blank calendar starting on month of first argument
# and continuing for second argument months
# https://leancrew.com/all-this/2023/09/a-shell-script-for-blank-calendars/
bcal() {
	usage="Usage: bcal [-n] m c
    Arguments:
    m  starting month number (defaults to this year)
    c  count of months to print
    Option:
    -n  use next year instead of this year"

	# Current year
	y=$(date +%Y)

	# If user asks for next year (-n), add one to the year
	while getopts "nh" opt; do
		case ${opt} in
		n) y=$((y + 1)) ;;
		h)
			echo "$usage"
			exit 0
			;;
		?)
			echo "$usage"
			exit 1
			;;
		esac
	done

	# Skip over any options to the required arguments
	shift $(($OPTIND - 1))

	# Exit with usage message if there aren't two arguments
	if (($# < 2)); then
		echo "Needs two arguments"
		echo "$usage"
		exit 1
	fi

	# Make the calendar, convert to PDF, and open in Preview
	pcal -e -S $1 $y $2 | ps2pdf - - | open -f -a Preview
}
