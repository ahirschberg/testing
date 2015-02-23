def new_divide(a, b) # sick linkin park reference, brah
  return "cannot divide by 0" if b == 0
  return a if b == 1
  cycles = 0
  while a - b >= 0
    a -= b
    cycles += 1
  end
  return cycles
end

p new_divide 0, 0
p new_divide 1, 0
p new_divide 1, 1
p new_divide 10, 5
#p new_divide 1000000000000, 12345

