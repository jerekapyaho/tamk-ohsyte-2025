with Ada.Text_IO;
with Ada.Integer_Text_IO;
with Ada.Calendar;
with Ada.Strings.Unbounded; use Ada.Strings.Unbounded;
with Ada.Containers.Vectors;
with Events;

procedure Today is
   All_Events : Events.Event_Vectors.Vector;

begin
   All_Events := Events.Test_Events;
   Events.Sort (All_Events);

   for Event of All_Events loop
      Events.Put (Event);
   end loop;
end Today;
