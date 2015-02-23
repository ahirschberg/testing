class Point 
  def initialize(id, x, y)
    @id, @x, @y = id, x, y
    @links = []
  end

  def distance(point)
    Math.hypot(point.x - self.x, point.y - self.y) # neat!
  end

  def link_prev; @links[0]; end
  def link_prev=(arg)
    @links[0] = arg
  end

  def link_next; @links[1]; end
  def link_next=(arg)
    @links[1] = arg
  end

  def to_s
    #return "#<Point id=#{id} (#{@x}, #{@y}) links=[#{@links.first.id if links.first}, #{@links.last.id if links.last}]>"
    return "#<Pt #{id} links=[#{@links.map {|l| l.id}.join ", "}]>"
  end
  def to_str; to_s; end

  attr_accessor :id, :x, :y, :links
end

module PointGenerator
  def self.generate_random_points(x_range, y_range, num_points)
    (0..num_points).map {|i| Point.new i, rand(x_range), rand(y_range)}
  end

  def self.nearest_neighbor(points)
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
      curr_point.link_next = min_dist_pt
      curr_point       = curr_point.link_next
      total_distance  += min_dist
    end

    # connect last and first elements
    total_distance += curr_point.distance(points.first)
    curr_point.link_next = points.first

    return points, total_distance
  end

  #god this is terrible...
  def self.closest_pair(points)
    total_distance = 0
    points.each do
      min_distance = Float::INFINITY
      min_dist_point1 = nil
      min_dist_point2 = nil
      points.each do |point1|
        next if point1.links.length >= 2
        points.each do |point2|
          next if point2.links.length >= 2 or point2 == point1 or point1.links.include?(point2) or point2.links.include?(point1) 
          # ensure points are not already linked
          if point1.links & point2.links == []
            pt_distance = point1.distance(point2)
            if pt_distance <= min_distance and !chain_contains(point1, point2)
              min_distance = pt_distance
              min_dist_point1 = point1
              min_dist_point2 = point2
            end
          end
        end
      end
      puts points.join(", ")
      if min_distance == Float::INFINITY
        puts "No more points!"
        endpoint = nil
        points.each do |point|
          if point.links.length == 1
            if endpoint
              endpoint.links << point
              point.links << endpoint
              total_distance += point.distance(endpoint)
              break
            else
              endpoint = point
            end
          end
        end
        break
      end
      puts "Min distance was #{min_dist_point1.id} to #{min_dist_point2.id}\n\n"
      min_dist_point1.links << min_dist_point2
      min_dist_point2.links << min_dist_point1
      total_distance += min_distance
    end
    return points, total_distance
  end

  def self.chain_contains(chain_member, test_point)
    prev_member = nil
    while chain_member
      return true if chain_member == test_point
      # dangerous assumptions?
      jump_to_next = chain_member.links - [prev_member]
      prev_member = chain_member
      chain_member = jump_to_next.first
    end
    return false
  end

  def self.print_chain(points)
    puts points
    curr_point = points.first
    iter = 0
    while iter <= points.length
      puts curr_point.id
      curr_point = curr_point.links.first

      break if curr_point == points.first # prevent infinite loop
      iter += 1
    end
  end
end

def output_point_data(points)
  sorted_points, distance = PointGenerator::closest_pair(points)
  puts sorted_points
  puts "Total distance: #{distance}"
end

if __FILE__ == $0
  #puts "Random data:"
  #random_points = PointGenerator::generate_random_points 10, 10, 10
  #output_point_data random_points
  
  puts "\nPathological data:"
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
  output_point_data evil_points1
  puts "\nPathological data 2:"
  output_point_data evil_points2
  end
