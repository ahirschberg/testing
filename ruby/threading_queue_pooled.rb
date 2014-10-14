class ThreadPool
  def initialize(num_threads)
    @parsers  = Queue.new
    @lock     = Mutex.new
    @resource = ConditionVariable.new

    num_threads.times.each_with_index do |i| 
      @parsers << Thread.new do
        until @finished do
          task = nil
          @lock.synchronize do
            @resource.wait(@lock)
            task = @task_queue.pop
          end
            #sleep 0.1
            puts "T#{i} begins #{task.id}"
            task_data = task.call
            sleep rand() * 10
            puts "T#{i} completes #{task.id}: #{task_data}"
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
    "#<Task: @id=#{@id}"
  end
  attr_reader :id
end

Thread.abort_on_exception = true

tp = ThreadPool.new 4

i = 0
loop do
  tp.push Task.new(i) {rand() * rand()}
  puts "adding #{i} to queue #{tp.task_queue.inspect}"
  tp.resource.signal
  sleep 1
  i+=1
end
