class ThreadPool
  def initialize(num_threads)
    @threads  = Array.new
    @lock     = Mutex.new
    @resource = ConditionVariable.new

    num_threads.times.each_with_index do |i| 
      @threads << Thread.new do
        until @finished do
          task = nil
          @lock.synchronize do
            @resource.wait(@lock)
            task = @task_queue.pop
          end
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

  def join_all # does not work correctly
    @threads.each {|thread| thread.join}
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
    "#<Task: @id=#{@id}"
  end
  attr_reader :id
end

Thread.abort_on_exception = true

tp = ThreadPool.new 4

i = 0
until i > 20 do
  tp.push Task.new(i) {"..."}
  puts "adding #{i} to queue #{tp.task_queue.inspect}"
  tp.resource.signal
  i+=1

  sleep rand()
end
#tp.join_all
