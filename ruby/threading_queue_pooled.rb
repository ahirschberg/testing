class ThreadPool
  def initialize(num_threads)
    @threads      = Array.new
    @lock         = Mutex.new
    @task_queue   = Queue.new
    @finished     = false

    populate_workers @threads, num_threads
  end
  
  def populate_workers(workers_collection, num_workers)
    num_workers.times do |i| 
      workers_collection << Thread.new do

        until @task_queue.empty? and @finished do
          task = @lock.synchronize do #not sure if this is necessary
            @task_queue.pop
          end
          puts "T#{i} begins #{task.id}"
          task_data = task.call
          sleep rand() * 10
          puts "T#{i} completes #{task.id}: #{task_data}"
        end
      end

    end
  end

  def push(task)
    @task_queue.push task
  end

  def join_all
    @finished = true
    if @task_queue.empty? #prevents deadlock for infinite waiting on Queue#pop
      self.kill_all
    else
      @threads.each {|thread| thread.join}
    end
  end

  def kill_all
    @finished = true
    @threads.each {|thread| thread.kill}
  end

  attr_accessor :finished, :resource
  attr_reader :task_queue #debug
end  

class Task
  def initialize(id, &block)
    @id    = id
    @block = block
  end
  def call
    @block.call
  end
  def to_s
    "#<Task: @id=#{@id}>"
  end
  attr_reader :id
end

Thread.abort_on_exception = true

tp = ThreadPool.new 4

i = 0
20.times do
  tp.push Task.new(i) {"..."}
  puts "adding #{i} to queue" ##{tp.task_queue.inspect}"
  i+=1

  sleep rand()
end
puts "join started"
tp.join_all
puts "join completed"
