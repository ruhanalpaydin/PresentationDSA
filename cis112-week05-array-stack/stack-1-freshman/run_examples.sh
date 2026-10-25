#!/bin/bash
# =====================================================================
#  run_examples.sh -- run every example and save what it printed
#  ---------------------------------------------------------------------
#  codeoutput/<Name>.java   a class; if it has main(), it is compiled and run:
#                           output (stdout and stderr) -> codeoutput/<Name>.txt
#                           classes without main() (e.g. ArrayStack) are only compiled;
#                           other programs find them through -sourcepath codeoutput
#  codeoutput/<Name>.in       keyboard input            (optional)
#  codeoutput/<Name>.args     command line arguments    (optional)
#  codeoutput/<Name>.jvmargs  JVM options, e.g. -ea     (optional)
#  codeoutput/<Name>.sh     a terminal session, run in a fresh copy of codeoutput/<Name>/;
#                           each line is shown as "$ line" with its output;
#                           lines starting with "#@ " run silently (set-up)
#
#  Usage (macOS Terminal, Linux, Windows Git Bash):
#     bash run_examples.sh                run all
#     bash run_examples.sh Push.java      run one
# =====================================================================
cd "$(dirname "$0")" || exit 1
unset JAVA_TOOL_OPTIONS          # some systems set it; it adds a line to every output
here=$(pwd)
rm -rf build; mkdir -p build
session() {                      # session <file> <output> <folder to copy>
    work=$(mktemp -d "${TMPDIR:-/tmp}/session.XXXXXX")
    [ -d "$3" ] && cp -R "$3/." "$work/"
    (
        cd "$work" || exit 1
        export LC_ALL=C.UTF-8 TZ=Europe/Istanbul; rc=0
        while IFS= read -r line <&3 || [ -n "$line" ]; do
            case "$line" in
                '#@ '*) eval "${line#\#@ }" </dev/null >/dev/null 2>&1 ;;
                ''|'#'*) ;;
                *) printf '$ %s\n' "$line"; (exit $rc); eval "$line" </dev/null 2>&1; rc=$? ;;
            esac
        done 3< "$here/$1"
    ) > "$2.tmp" 2>&1
    sed -e "s#$work#~/project#g" -e "s#$(dirname "$work")/session\.[A-Za-z0-9]*#~/project#g" "$2.tmp" > "$2"
    rm -f "$2.tmp"; rm -rf "$work"
}
if [ $# -gt 0 ]; then
    files=$(for f in "$@"; do echo "codeoutput/$f"; done)
else
    files=$(ls codeoutput/*.java codeoutput/*.sh 2>/dev/null)
fi
for f in $files; do
    name=$(basename "$f"); base=${name%.*}; out="codeoutput/$base.txt"
    in="codeoutput/$base.in"; [ -f "$in" ] || in=/dev/null
    case "$name" in
        *.java)
            if ! grep -q "static void main" "$f"; then          # a library class: compile only
                javac -encoding UTF-8 -d build -sourcepath codeoutput "$f" || exit 1
                continue
            fi
            args="";    [ -f "codeoutput/$base.args" ]    && args=$(cat "codeoutput/$base.args")
            jvmargs=""; [ -f "codeoutput/$base.jvmargs" ] && jvmargs=$(cat "codeoutput/$base.jvmargs")
            if javac -encoding UTF-8 -d build -sourcepath codeoutput "$f" > "$out" 2>&1; then
                java -Dstdout.encoding=UTF-8 $jvmargs -cp build "$base" $args < "$in" > "$out" 2>&1
            fi ;;
        *.sh)   session "$f" "$out" "codeoutput/$base" ;;
        *)      continue ;;
    esac
    echo "ran   $f -> $out"
done
