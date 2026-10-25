javap -p java.util.ArrayDeque | sed -n '3,8p'
javap -p java.util.ArrayDeque | grep -E " (inc|dec|sub|elementAt)\("
