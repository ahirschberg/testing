class ThreadPool
  def initialize(num_threads)
    @parsers      = Queue.new
    num_threads.times.each do 
      @parsers << Thread.new do
        until @finished do
          puts "worker called"
          task = @task_queue.deq
          puts "dequeued #{task}"
          task.call
        end
      end
    end

    @task_queue   = Queue.new
    @finished     = false
  end
  
  def push(task)
    puts "#{task} pushed to task_queue"
    @task_queue.push task
  end

  attr_accessor :finished
end  

Thread.abort_on_exception = true

tp = ThreadPool.new 4

i = 0
loop do
  tp.push lambda {puts "widget #{i}"}
  sleep 1
  i+=1
end
