require 'thread'

class ThreadPool
  def initialize(num_threads)
    @parsers  = Queue.new
    @lock     = Mutex.new
    @resource = ConditionVariable.new

    num_threads.times.each_with_index do |i| 
      @parsers << Thread.new do
        until @finished do
          @lock.synchronize do
            @resource.wait(@lock)
            task = @task_queue.deq 
            @resource.signal

            sleep rand() * 10
            task_number = task.call
            puts "T#{i} completes #{task_number}"
          end
        end
      end
    end

    @task_queue   = Queue.new
    @finished     = false
  end
  
  def push(task)
    @task_queue.push task
  end

  attr_accessor :finished, :resource
end  

Thread.abort_on_exception = true

tp = ThreadPool.new 4

i = 0
loop do
  puts "adding #{i} to queue"
  tp.push lambda {i}
  tp.resource.signal
  sleep 0.5 * i
  i+=1
end
