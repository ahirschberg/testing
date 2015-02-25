def colorize(text, color_code)
  "\e[#{color_code}m#{text}\e[0m"
end

class Point 
  def initialize(id, x, y)
    @id, @x, @y = id, x, y
  end

  def distance(point)
    Math.hypot(point.x - self.x, point.y - self.y) # neat!
  end

  def to_s
    return "#<Point id=#{id} (#{@x}, #{@y})>"
    #return "#<Pt #{@id} links=[#{@links.map {|l| l.id}.join ", "}]>"
  end

  attr_accessor :id, :x, :y
end

class SingleLinkedPoint < Point
  def initialize(id, x, y)
    super(id, x, y)
    @link = nil
  end

  def to_s
    return "#<Point id=#{id} (#{@x}, #{@y}) link=#{@link.id}>"
  end
  
  def self.convert_point(point)
    return SingleLinkedPoint.new point.id, point.x, point.y
  end

  attr_accessor :link
end

class DoubleLinkedPoint < Point
  def initialize(id, x, y)
    super(id, x, y)
    @links = []
  end
  
  def to_s
    return "#<Point id=#{id} (#{@x}, #{@y}) links=[#{@links.map{|l| l.id}.join(', ')}]>"
  end
  
  def self.convert_point(point)
    return DoubleLinkedPoint.new point.id, point.x, point.y
  end

  attr_accessor :links
end

module SolveTSP

  module NearestNeighbor
    def self.solve(points)
      points = points.map {|point| SingleLinkedPoint.convert_point point}
      unchecked_points = points.clone[1..-1] # chop off 0th element
      curr_point = points.first
      total_distance = 0
      while !unchecked_points.empty?
        min_dist_pt = nil
        min_dist    = Float::INFINITY
        
        unchecked_points.each do |point| 
          dist_to_pt = point.distance(curr_point) 
          if dist_to_pt < min_dist
            min_dist    = dist_to_pt
            min_dist_pt = point
          end
        end

        unchecked_points.delete min_dist_pt
        curr_point.link = min_dist_pt
        curr_point       = curr_point.link
        total_distance  += min_dist
      end

      # connect last and first elements
      total_distance += curr_point.distance(points.first)
      curr_point.link = points.first

      return points, total_distance
    end
  end

  class ClosestPair
    def self.solve(points)
      points = points.map {|point| DoubleLinkedPoint.convert_point point}
      total_distance = 0
      points.each do
        min_distance = Float::INFINITY
        min_dist_point1 = nil
        min_dist_point2 = nil
        points.each do |point1|
          next if point1.links.length >= 2 # if point1 already has two connections
          points.each do |point2|
            next if point2.links.length >= 2 or chain_contains(point1, point2) 
              pt_distance = point1.distance(point2)
              if pt_distance <= min_distance
                min_distance = pt_distance
                min_dist_point1 = point1
                min_dist_point2 = point2
              end
            end
        end
        #puts points.join(", ")
        if min_distance == Float::INFINITY
          endpoint = nil
          points.each do |point|
            if point.links.length == 1
              if endpoint
                endpoint.links << point
                point.links << endpoint
                total_distance += point.distance(endpoint)
                #puts "  Min distance was endpoints #{endpoint.id} to #{point.id}"
                break
              else
                endpoint = point
              end
            end
          end
          break
        end
        #puts "  Min distance was #{min_dist_point1.id} to #{min_dist_point2.id}"
        min_dist_point1.links << min_dist_point2
        min_dist_point2.links << min_dist_point1
        total_distance += min_distance
      end
      return points, total_distance
    end

    def self.chain_contains(test_point_a, test_point_b)
      #puts "Checking #{test_point_a} for #{test_point_b}"
      return true if test_point_a == test_point_b

      test_point_a.links.each do |linked_point|
        prev_member = test_point_a
        while linked_point
          #puts "> Checking #{linked_point} == #{test_point_b}: #{linked_point == test_point_b}"
          return true if linked_point == test_point_b

          jump_to_next = linked_point.links - [prev_member] #remove 
          #puts "> got #{jump_to_next} from #{linked_point.links.join ","}"
          prev_member = linked_point
          linked_point = jump_to_next.first
        end
      end
      return false
    end
  end
end

module PointGenerator
  def self.generate_random_points(x_range, y_range, num_points)
    (0..num_points).map {|i| Point.new i, rand(x_range), rand(y_range)}
  end
end

def output_point_data(point_cloud_title, points)
  puts
  puts "=#{point_cloud_title}="
  [SolveTSP::NearestNeighbor, SolveTSP::ClosestPair].each do |strategy| 
    puts "Using #{colorize(strategy.name, 32)}"
    sorted_points, distance = strategy.solve(points)
    #puts "#{sorted_points.join "\n"}"
    puts colorize("Total distance: #{distance}", 33)
  end
end

if __FILE__ == $0
  if ARGV[0] == "-r"
    num_iter = ARGV.length > 1 ? ARGV[1].to_i : 10
    num_iter.times do |i|
      random_points = PointGenerator::generate_random_points 10, 10, 10
      output_point_data "Random Data #{i + 1}", random_points
    end
  else 
    evil_points1 = [
                    Point.new(0, 0, 10),
                    Point.new(1, 0, 11),
                    Point.new(2, 0, 9 ),
                    Point.new(3, 0, 13),
                    Point.new(4, 0, 4 ),
                  ]
    evil_points2 = [
                    Point.new(1,0,1),
                    Point.new(2,2,1),
                    Point.new(3,4,1),
                    Point.new(4,0,0),
                    Point.new(5,2,0),
                    Point.new(6,4,0),
                  ]
    output_point_data "The Straight Line of Doom", evil_points1
    output_point_data "The Rectangle of Terror", evil_points2
  end
end
