import React, { useState } from 'react';

function JobsPage() {
  const [selectedOption, setSelectedOption] = useState("demo");

  const handleOptionChange = (event) => {
    setSelectedOption(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    // handle form submission logic here
  };

  return (
    <div>
      <h2 style={{ textDecoration: "underline" }}>Batch Jobs</h2>
      <div className="d-flex align-items-center">
        <form onSubmit={handleSubmit} className="d-flex align-items-center">
          <select
            className="form-select me-2"
            aria-label="Job Type"
            value={selectedOption}
            onChange={handleOptionChange}
            style={{ minWidth: "300px" }}
          >
            <option value="demo">Demo</option>
            <option value="full-time">Full-time</option>
            <option value="part-time">Part-time</option>
          </select>
          <button type="submit" className="btn btn-primary ms-auto">
            Submit
          </button>
        </form>
      </div>
      {/* add other content here */}
    </div>
  );
}

export default JobsPage;
