# Test class for managing downloading and parsing in separate threads
# Commented to be readable for someone not familiar with ruby
class Parser
  @@END_PARSE_SYM = :END_PARSE # class constant which signals that there are no more items to be parsed

  def initialize()
    @queue = Queue.new # queue is a thread safe list-like element 
  end

  def add(*files)
    files.each {|file| @queue << file } # add each file to the queue
  end

  def finish
    puts "Sending finish message to queue"
    @queue << @@END_PARSE_SYM
  end

  def parse # the code that calls this method is responsible for threading
    while true do
      puts "Waiting for queue" if @queue.empty?
      file = @queue.pop # this method is blocking if @queue is empty
      break if file == @@END_PARSE_SYM # if the end parse message is encountered, break the loop
      puts "Beginning parse of #{file}..."
      sleep rand() * 2
      puts "Parsed #{file}."
    end
  end
end

class Downloader
  def initialize(parser, desired_files)
    desired_files.each do |file|
      sleep_secs = rand() * 2
      puts "Downloading #{file}"
      sleep sleep_secs
      puts "Downloaded #{file} in #{sleep_secs} seconds."
      parser.add file
    end
  end
end

parser = Parser.new 
thread = Thread.new { parser.parse } # call parser.parse in a new thread
downloader = Downloader.new parser, %w[file1 file2 file3]
parser.finish # let parser know that there are no more files to wait for
thread.join
puts "Parsing completed."
