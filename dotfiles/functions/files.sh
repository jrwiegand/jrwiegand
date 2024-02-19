## find all the things
sf() {
	find "$1" -iname "$2";
}
ssf() {
	sudo find "$1" -iname "$2";
}

## find all the things in files
ss() {
	rg -F -i -p "$1" "$2";
}
sss() {
	sudo rg -F -i -p "$1" "$2";
}

## delete all files with this name
del() {
	find ~/ -name "$1" -type f -delete;
}
sdel() {
	sudo find / -name "$1" -type f -delete;
}

## edit file or files with sed
ef() {
	find "$1" -type f -exec sed -i "" "s/$2/$3/g" {} \;
}
sef() {
	sudo find "$1" -type f -exec sed -i "" "s/$2/$3/g" {} \;
}
