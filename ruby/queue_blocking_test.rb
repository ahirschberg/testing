# script just double checking that Queue.deq can be used concurrently by multiple threads
require 'thread'

q = Queue.new

builder = Thread.new do
  i = 0
  loop do 
    q.push "widget #{i+=1}"
    sleep 0.5
  end
end

t1 = Thread.new { loop { puts "t1: #{q.deq}"; sleep 1} }
t2 = Thread.new { loop { puts "t2: #{q.deq}"; sleep 2 } }
t3 = Thread.new { loop { puts "t3: #{q.deq}"; sleep 10 } }

sleep
