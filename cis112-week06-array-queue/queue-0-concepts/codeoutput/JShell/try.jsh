var q = new java.util.ArrayDeque<Integer>();
q.offer(10);
q.offer(20);
q.offer(30);
System.out.println("queue: " + q + "   front = " + q.peek());
System.out.println("poll -> " + q.poll());
System.out.println("poll -> " + q.poll());
System.out.println("queue: " + q + "   size = " + q.size());
/exit
