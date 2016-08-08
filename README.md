# Demo4SwipeRecyclerview
swipe item for recyclerview

use swipelayout(https://github.com/daimajia/AndroidSwipeLayout) and do some change.

if you need some item can not be swiped,
just set the width and height of the layout which is the first child for swipelayout by wrap_content or 0.(now you can use isSwipeEnable(int position) in adapter to forbid swipe)

recommend extends SimpleSwipeAdapter
