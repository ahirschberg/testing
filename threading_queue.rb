class Parser
  END_PARSE_SYM = :END_PARSE
  def initialize()
    @queue = Queue.new
  end

  def add(*files)
    files.each {|file| @queue << file } #better way to do this?
  end

  def finish
    puts "Sending finish message to queue"
    @queue << END_PARSE_SYM
  end

  def parse # the code that calls this method is responsible for threading
    while true do
      puts "Waiting for queue" if @queue.empty?
      file = @queue.pop
      break if file == END_PARSE_SYM
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
thread = Thread.new { parser.parse }
downloader = Downloader.new parser, %w[file1 file2 file3]
parser.finish
thread.join
puts "Parsing completed."
